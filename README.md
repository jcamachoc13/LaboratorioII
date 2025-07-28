# Laboratorio2

La relacion N:N funciona de modo donde una entidad va a estar relacionada con muchos objetos en otro y a su vez un objeto de esta segunda entidad tambien va a estar relacionada con muchas en la primera, a nivel practico estas relaciones en un modelo entidad-relacion son tablas intermedias entre 2 tablas que permiten que las mismas esten relacionadas entre multiples objetos.  En este caso para hacer esta relacion al hacer la definicion de una relacion many to many en los modelos en la base de datos se crea una tabla intermedia personas_curso donde se van a almacenar los id de personas vrs los id de cursos que vayamos a relacionar  

Para este laboratorio se crea un archivo Curso en el modelo de datos donde se define una relacion con personas

  Curso:
    @ManyToMany(mappedBy = "cursos")
    private List<Persona> personas = new ArrayList<>();
    
  Persona:
    @ManyToMany
    @JoinTable(
        name = "persona_curso",
        joinColumns = @JoinColumn(name = "persona_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )

Ya con la relacion definida, seguimos la logica vista en clase para la mayoria de objetos java dentro del proyecto, solo que para poder definir las relaciones con persona incluimos este modelo en el controlador, tambien se definen en los atributos listados de cursos, esto para que en las vistas de incluir y modificar podamos agregar un checkbox con el listado de los cursos que se agregaron.

Para efectos practicos se crearon vistas de cursos x persona y personas del curso, donde se llenan tablas como en ejemplos anteriores vistos en clases 




