from json import loads
from os import getenv

from addict import Dict
from dotenv import load_dotenv
from groq import Groq
from regex import compile

load_dotenv()


def extract_json(string):
    pattern = compile(r"\{(?:[^{}]|(?R))*\}")
    matches = pattern.findall(string)
    if matches:
        return matches[0]


class LLM:
    def __init__(self):
        self.api = Groq(api_key=getenv("GROQ_API_KEY"))

    def call(self, system_prompt, user_prompt):
        try:
            print("GROQ SENDING TO AI")
            chat_completion = self.api.chat.completions.create(
                model="llama3-8b-8192",
                # model="llama3-70b-8192",
                messages=[
                    {
                        "role": "system",
                        "content": system_prompt,
                    },
                    {
                        "role": "user",
                        "content": user_prompt,
                    }
                ],
                    #
                # Optional parameters
                #

                # Controls randomness: lowering results in less random completions.
                # As the temperature approaches zero, the model will become deterministic
                # and repetitive.
                # temperature=0.5,

                # The maximum number of tokens to generate. Requests can use up to
                # 32,768 tokens shared between prompt and completion.
                # max_tokens=1024,

                # Controls diversity via nucleus sampling: 0.5 means half of all
                # likelihood-weighted options are considered.
                # top_p=1,

                # A stop sequence is a predefined or user-specified text string that
                # signals an AI to stop generating content, ensuring its responses
                # remain focused and concise. Examples include punctuation marks and
                # markers like "[end]".
                # stop=None,

                # If set, partial message deltas will be sent.
                # stream=False,
            )
            content = chat_completion.choices[0].message.content
        except Exception as e:
            return str(e)
        return content