# AppFilmes

IMPORTANTE!
Criar um repository no Github:

    Colocar o código de vocês na branch MAIN;

    Histórico completo de commits;

    Mínimo de 5 commits;

    Código copiado será ZERO para ambos;



Criar o aplicativo em Kotlin:

    Criar uma tela inicial que mostre os 20 primeiros filmes mais populares e paginação;

    Criar uma tela de detalhes dos filmes;

    Tratar parâmetros nulos;

    Fazer navegação de uma tela para outro;



Serviço e funcionalidades:

    Criar retrofit para fazer as requests;

    Criar recycler view para listar os resultados;

    Paginação recyclerView;



URL BASE: https://api.themoviedb.org/3/



METHOD: GET movie/popular

    Obtenha uma lista dos filmes populares atuais no TMDB. Esta lista é atualizada diariamente.



    https://api.themoviedb.org/3/movie/popular?api_key=9106a44c761c36bbb02f24c16958a56a



METHOD: GET movie/{movie_id}

    Obtenha as informações primárias sobre um filme.



    https://api.themoviedb.org/3/movie/550?api_key=9106a44c761c36bbb02f24c16958a56a



Carregar imagens:

    As imagens da API são separadas dos endpoints, para carregar uma imagem é necessário utilizar a url https://image.tmdb.org/t/p/w500 + o id da imagem, por exemplo, /wwemzKWzjKYJFfCeiB57q3r4Bcm.png


    Exemplo: https://image.tmdb.org/t/p/w500/wwemzKWzjKYJFfCeiB57q3r4Bcm.png



Autenticação:
    Essa API requer uma chave API para acessar as informações dos bancos de dados, esse parâmetro será passado como uma query em todas as requests solicitadas.



    Por exemplo: https://api.themoviedb.org/3/movie/550?api_key=9106a44c761c36bbb02f24c16958a56a



    No method de vocês, deve ser passado uma query contendo a API_KEY:

    @Query(value = “api_key”) key: String



A chave API de vocês será essa “9106a44c761c36bbb02f24c16958a56a”



Opcional:
    Usar interceptor para enviar a chave da api em todas as requests.

Paging 3 library android, para fazer a paginação no recyclerview.

    Picasso e Glide para carregar imagens
