package api

class GreetingsController {
    def index(PersonCommand person) {
        if (person.hasErrors()) {
            response.status = 400
            respond(message: "Please check name and age")
            return
        }

        if (person.name == 'mario') {
            respond(message: "You're awesome!")
        } else {
            respond(message: "You're not so awesome ${person.name} you look older than ${person.age}")
        }
    }
}
