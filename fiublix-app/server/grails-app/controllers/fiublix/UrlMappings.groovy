package fiublix

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/user"(resources:"user")
        
        get "/user/$userId/circle"(controller: 'user', action: 'getCircleMovies')

        post "/user/$userId/addFriend"(controller: 'user', action: 'addFriend')
        post "/user/$userId/addFavoriteMovie"(controller: 'user', action: 'addMovie')
        post "/user/$userId/recommendMovie"(controller: 'user', action: 'recommendMovie')

        "/movies"(resources:"movie")

        post "/movies/$movieid/similar"(controller: 'movie', action: 'searchSimilarMovie')


        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
