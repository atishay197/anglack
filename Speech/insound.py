import sounddevice as sd

fs = 44100
sd.default.samplerate = fs
sd.default.channels = 2
duration = 10  # seconds
myrecording = sd.rec(duration)
sd.play(myrecording)

print myrecording