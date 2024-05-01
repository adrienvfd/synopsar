from flask import Flask, request, jsonify
from prompts import Prompts
from llm import LLM

app = Flask(__name__)

llm = LLM()

@app.route('/summarize', methods=['POST'])
def summarize():
    system_prompt = Prompts.summarize
    user_prompt = request.get_json()
    response = llm.call(system_prompt, user_prompt)
    return response
    # return jsonify({'response': response})

if __name__ == '__main__':
    ## run app on port 5001
    app.run(host='0.0.0.0', debug=False, port=5001)

