class Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private int numeroTelefono;
    private String correo;
    
    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Cliente(String nombre, String apellido, String dni) {
        this(nombre, apellido);
        this.dni = dni;
    }
    
    public Cliente(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
    public void actualizarNombre(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public void actualizarDni(String dni) {
        this.dni = dni;
    }
    
    public void actualizarTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
    public void actualizarCorreo(String correo) {
        this.correo = correo;
    }
}