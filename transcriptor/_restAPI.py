from flask  import Flask
from flask_restful import Api, Resource
from youtube_transcript_api import YouTubeTranscriptApi

app = Flask(__name__)
api = Api(app)

class Transcript(Resource):
    def get(self, video_id):
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        return transcript
api.add_resource(Transcript, "/transcript/<string:video_id>")

if __name__ == "__main__":
    app.run(debug=True)
