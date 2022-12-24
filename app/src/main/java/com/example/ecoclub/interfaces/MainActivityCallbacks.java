package com.example.ecoclub.interfaces;

import com.example.ecoclub.Entities.Usuario;
import com.google.android.gms.maps.model.LatLng;

public interface MainActivityCallbacks {
    //mensaje desde el fragment al main
    void changeFragmentInMain(String destiny);
    void requestCurrentUserDataInMain();
    Usuario sendCurrentUserDataFragment();
    void logout();
    void enviarUbicacion(String fragment, LatLng ubicacion);
}
