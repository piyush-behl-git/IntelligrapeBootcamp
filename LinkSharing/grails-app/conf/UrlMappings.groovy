class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/" {
            controller = 'user'
            action = 'dashboard'
        }
        "500"(view: '/error')
    }
}
