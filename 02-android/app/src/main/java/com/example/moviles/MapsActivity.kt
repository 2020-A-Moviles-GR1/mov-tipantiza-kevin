package com.example.moviles

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener

{



    private lateinit var mMap: GoogleMap
    var tienePermisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //1) Permisos
        establecerConfiguracionMapa(mMap)
        establecerListener(mMap)

        val cci = LatLng(-0.178067, -78.485594)
        val puntoUsuario = LatLng(-0.179204, -78.484189)
        val titulo="cci"
        val zoom = 17f
        anadirMarcador(cci,titulo)
        moverCamaraConZoom(puntoUsuario, zoom)

        val poliLineaUno = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(-0.178866, -78.484525),
                    LatLng(-0.178614, -78.485920),
                    LatLng(-0.176179, -78.485373),
                    LatLng(-0.176404, -78.483522)
                )
        )
        val poligonoUno = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                        LatLng(-0.178854, -78.485814),
                        LatLng(-0.178951, -78.485290),
                        LatLng(-0.179450, -78.485360),
                        LatLng(-0.179359, -78.485939)

                )
        )
        poligonoUno.fillColor = -0xc771c4

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom:Float =10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirMarcador(latLng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                Log.i("mapa", "Tiene permisos FINE LOCATION")
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true

        }
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

        val tienePermisos = permisosFineLocation ==PackageManager.PERMISSION_GRANTED

        if(tienePermisos){

            Log.i("mapa","tiene permisos FINE LOCATION")
            this.tienePermisos = true

        }else{
            ActivityCompat.requestPermissions(
                this,  //contexto
                arrayOf( //Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //codigo que esperamos
            )

        }

    }

    fun establecerListener(map: GoogleMap){

        with(map){
            setOnCameraIdleListener ( this@MapsActivity )
            setOnCameraMoveStartedListener( this@MapsActivity )
            setOnCameraMoveListener( this@MapsActivity )
            setOnPolylineClickListener( this@MapsActivity )
            setOnPolygonClickListener ( this@MapsActivity )

        }

    }

    override fun onCameraMoveStarted(p0: Int) {
       Log.i("mapa", "Empezando a mover onCameraMoveStarted")
    }

    override fun onCameraMove() {
        Log.i("mapa", "Moviendo onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("mapa", "Quieto onCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa", "Polylinea ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa", "Polygono ${p0.toString()}")
    }
}
