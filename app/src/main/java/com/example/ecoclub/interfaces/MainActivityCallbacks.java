package com.example.ecoclub.interfaces;

import com.example.ecoclub.Entities.Usuario;

public interface MainActivityCallbacks {
    //mensaje desde el fragment al main
    void changeFragmentInMain(String destiny);
    void requestCurrentUserDataInMain();
    Usuario sendCurrentUserDataFragment();
}
