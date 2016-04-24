#screen manager
from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager, Screen, WipeTransition
from kivy.core.image import Image

#from kivy.core.window import Window
#Window.size = (350, 550)

# Create both screens. Please note the root.manager.current: this is how
# you can control the ScreenManager from kv. Each screen has by default a
# property manager that gives you the instance of the ScreenManager used.
Builder.load_string("""
#:import Image kivy.core.image.Image
#:import pasval passing.pasval
#:import intg Integrator.intg
<MenuScreen>:
    BoxLayout:
        orientation: 'vertical'
        padding: 20
        Label:
            halign: 'center'
            text: "Able"
            size_hint: 1,0.1
        Button:
            text: 'Document Scanner '
            on_press: 
                root.manager.current = root.manager.next()
                pasval('1')

        Button:
            text: 'Know what around you'
            on_press: 
                root.manager.current = root.manager.next()
                pasval('2')
        
<SettingsScreen>:
    BoxLayout:
        orientation: 'vertical'
        Camera:
            id: camera
            resolution: (640, 480)
            play: True
            on_touch_down:
                #print(self.texture)
                #self.texture
                img = Image(self.texture)
                img.save('pics/img.JPG')
                root.manager.current='result'
                
                
<ResultScreen>:
    on_enter: intg()
    BoxLayout:
        
""")

#Declare both screens
class MenuScreen(Screen):
    pass

class SettingsScreen(Screen):
    pass
   
class ResultScreen(Screen):
    pass
        

# Create the screen manager
sm = ScreenManager(transition=WipeTransition())
sm.add_widget(MenuScreen(name='menu'))
sm.add_widget(SettingsScreen(name='settings'))
sm.add_widget(ResultScreen(name='result'))
class TestApp(App):

    def build(self):
        return sm

if __name__ == '__main__':
    TestApp().run()
