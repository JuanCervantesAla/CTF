@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {//Request the body params Json
        try {
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail().trim());//Filters the users by Email
            if(userOptional.isPresent()) {//If user is in the DB
                User user = userOptional.get();
                if(user.getPassword().equals(loginRequest.getPassword())) {// And the password matches
                    String token = jwtService.generateToken(loginRequest.getEmail());//Generates token
                    return ResponseEntity.ok(new ApiResponse("Inicio de sesion exitoso", user, token));
                } else {//If password doesnt match
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Contrasena incorrecta, intenta de nuevo"));
                }
            } else {//If theres not user
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("No se encontro el correo, ingresa de nuevo el correo correcto"));
            }
        } catch (Exception e) { //Catches the exception trying to not stop the execution of the program
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error durante el inicio de sesión", e.getMessage()));
        }
    }
