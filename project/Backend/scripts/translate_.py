from google.cloud import translate
import sys
import json

out_langs = ['de', 'fr', 'es', 'it', 'el', 'nl', 'ja', 'ko', 'zh-cn', 'hi', 'ru', 'id', 'ar']

with open(sys.argv[1], encoding='utf8') as f:
    data = json.load(f)
    translator = translate.Client()
    items = data.items()
    keys = [x[0] for x in items]
    values = [x[1] for x in items]

    for lang in out_langs:
        translated_values = []
        for v in values:
            translated = translator.translate(v, target_language=lang, source_language='en')
            print(v + ' -> ' + translated['translatedText'])
            translated_values.append(translated['translatedText'])

        translated_data = {}
        for i in range(len(keys)):
            translated_data[keys[i]] = translated_values[i]

        out = open(sys.argv[1].replace('.json', '_' + lang + '.json'), 'w', encoding='utf8')
        json.dump(translated_data, out, ensure_ascii=False)
        out.flush()
        out.close()

    sys.exit()
