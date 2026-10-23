package com.example.laborator4;

public class Mapa {

    private String denumire;
    private int numarFoi;
    private boolean areBuzunar;
    private String culoare;
    private String material;
    private float importanta;
    private boolean impermeabila;

    public Mapa(String denumire, int numarFoi,
                boolean areBuzunar, String culoare,
                String material, float importanta,
                boolean impermeabila) {

        this.denumire = denumire;
        this.numarFoi = numarFoi;
        this.areBuzunar = areBuzunar;
        this.culoare = culoare;
        this.material = material;
        this.importanta = importanta;
        this.impermeabila = impermeabila;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getNumarFoi() {
        return numarFoi;
    }

    public boolean isAreBuzunar() {
        return areBuzunar;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getMaterial() {
        return material;
    }

    public float getImportanta() {
        return importanta;
    }

    public boolean isImpermeabila() {
        return impermeabila;
    }

    @Override
    public String toString() {
        return "MAPA: " + denumire
                + "\nNumar foi: " + numarFoi
                + "\nAre buzunar: " + (areBuzunar ? "Da" : "Nu")
                + "\nCuloare: " + culoare
                + "\nMaterial: " + material
                + "\nImportanta: " + importanta + "/5"
                + "\nImpermeabila: " + (impermeabila ? "Da" : "Nu");
    }
}