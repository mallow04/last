from flask import Flask, render_template, request
import csv
import os

app = Flask(__name__)
#freezer = Freezer(app)

@app.route("/")
def home():
    return render_template('index.html')

@app.route("/result", methods=['POST'])
def result():
    base_dir = os.path.abspath(os.path.dirname(__file__))
    csv_file = os.path.join(base_dir, 'please.csv')
    with open(csv_file, 'r', newline='', encoding='utf-8') as f:
        reader = csv.reader(f)
        header = next(reader)
        for row in reader:
            if row[1] == request.form['class'] and row[2] == request.form['number']:
                return render_template('result.html', row=row, header=header)
        return render_template('result.html', row=None, header=header)

if __name__ == "__main__":
    app.run(debug=True)
