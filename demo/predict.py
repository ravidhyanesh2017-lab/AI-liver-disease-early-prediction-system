import sys
import numpy as np
import joblib

# load model and scaler
model = joblib.load("model.pkl")
scaler = joblib.load("scaler.pkl")

# read inputs from command line
values = [float(x) for x in sys.argv[1:]]

# convert to numpy
data = np.array(values).reshape(1, -1)

# scale values
data = scaler.transform(data)

# predict
prediction = model.predict(data)[0]

probability = model.predict_proba(data)[0][1]

if prediction == 1: 
    result = "Needs Evaluation"
else:
    result = "Normal"

print(result + "," + str(round(probability * 100, 2)))
