package api

class GoodbyesController {
    def index(PersonCommand command) {
        if (command?.name == 'mario') {
            respond(message: 'I hope to see u again pal')
        } else {
            respond(message: 'hit the road jack')
        }
    }
}
