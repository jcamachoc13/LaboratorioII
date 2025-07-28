# Laboratorio2
Se crea un archivo Curso en el modelo de datos donde se define una relacion con personas
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


