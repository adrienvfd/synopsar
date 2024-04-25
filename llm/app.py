from flask import Flask, request, jsonify
from prompts import Prompts
from llm import LLM

app = Flask(__name__)

llm = LLM()

@app.route('/summarize', methods=['POST'])
def summarize():
    data = request.get_json()
    system_prompt = Prompts.summarize
    formatted = llm.call_for_json(system_prompt)
    return jsonify(formatted)

if __name__ == '__main__':
    ## run app on port 5001
    app.run(port=5001, debug=False)
