import matplotlib.pyplot as plt
import glob
import sys
import numpy as np
import re
from collections import defaultdict
from colour import Color

files = []
for filename_glob in sys.argv[1:]:
    files = files + glob.glob(filename_glob)

data = defaultdict(list)
min_time = -1

for filename in files:
    with open(filename) as f:
        for line in f:
            match = re.search(r'.*?:\s+(\d+\.\d+),(\w+),(\d+\.\d+)', line)
            if match:
                if min_time == -1:
                    min_time = float(match[1])
                data[match[2]].append((float(match[1]) - min_time, float(match[3]) * 1000))
    min_time = -1

#if len(data) > 1:
#    colors = list(map(lambda x: x.get_hex(), Color('#330040').range_to(Color('#ff9d00'), len(data))))
#else:
#    colors = ['#333333']

nrows = int(len(data) / 2) + 1

i = 1
plt.figure(figsize=(10,19))
for method, values in data.items():
    v = sorted(values)
    x = [e[0] for e in v]
    y = [e[1] for e in v]
    plt.subplot(nrows, 2, i)
    plt.scatter(x, y, color='#000000', s=0.3, alpha=0.2)
    plt.title(method)
    plt.xlabel('time (s)', color='#666666')
    plt.ylabel('rt (ms)', color='#666666')
    i += 1
plt.subplots_adjust(wspace=0.35, hspace=0.55)
plt.savefig('logs.png', dpi=250, bbox_inches='tight')