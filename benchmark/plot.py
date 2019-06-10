import matplotlib.pyplot as plt
import csv
import glob
import sys
import numpy as np


def plot_barchart(data, title, y_label):
    y_pos = np.arange(len(data))
    plt.bar(y_pos, list(data.values()), color='#333333')
    plt.xticks(y_pos, list(data.keys()), rotation=45)
    plt.ylabel(y_label)
    plt.title(title)
    plt.savefig(title + '.png', dpi=250, bbox_inches='tight')


data = {}
files = []
for filename_glob in sys.argv[1:]:
    files = files + glob.glob(filename_glob)

# response time barchart
if len(files) == 1:
    with open(files[0]) as file:
        f = csv.reader(file)
        header = f.__next__()
        for row in list(f)[:-1]:
            data[row[header.index('Name')]] = float(row[header.index('Average response time')])
    plot_barchart(data, 'Methods response time', 'response time (ms)')


# thoughput barchart
elif len(files) > 1:
    for filename in files:
        with open(filename) as file:
            f = csv.reader(file)
            header = f.__next__()
            row = list(f)[-2]
            data[row[header.index('Name')]] = float(row[header.index('Requests/s')])
    plot_barchart(data, 'Methods throughput', 'requests/s')
        

else:
    quit('No files found')
