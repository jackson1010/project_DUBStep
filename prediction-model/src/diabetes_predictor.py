import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from flask import Flask, jsonify, request


df = pd.read_csv("../input/diabetes_binary_5050split_health_indicators_BRFSS2015.csv")

features = ['HighBP', 'HighChol', 'BMI','PhysActivity', 'Sex', 'Age']

df_x = df[features]
df_y = df['Diabetes_binary']

x_train, x_test, y_train, y_test = train_test_split(df_x, 
                                                    df_y, 
                                                    test_size=0.20, # 20% Testing Data
                                                    random_state=10)

diabetes_dtree = DecisionTreeClassifier(criterion='entropy')  

diabetes_dtree.fit(x_train, y_train)

full_results = diabetes_dtree.predict(x_test)
print("Full tree accuracy", diabetes_dtree.score(x_test, y_test))


app = Flask(__name__)


@app.route('/predict', methods=['POST'])
def predict_diabetes():
    patient_info = [
        [request.json['bp'], 
         request.json['chol'], 
         request.json['bmi'], 
         request.json['physicalActivity'], 
         request.json['sex'], 
         request.json['ageGroup']]
    ]
    patient_df = pd.DataFrame(patient_info)
    prediction = diabetes_dtree.predict(patient_df)
    print("result>> ", prediction[0])

    return jsonify({"probability" : prediction[0]})

if __name__ == '__main__':
    app.run(port=5000)
