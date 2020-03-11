package de.kohnlehome;

public class Auswertung implements IAuswertung {
    private ICoronaDaten coronaDaten;
    private IEinwohnerDaten einwohnerDaten;

    public Auswertung(ICoronaDaten coronaDaten, IEinwohnerDaten einwohnerDaten) {
        this.coronaDaten = coronaDaten;
        this.einwohnerDaten = einwohnerDaten;
    }

    @Override
    public double getInfizierteProzent(String land) {
        int einwohner = einwohnerDaten.getAnzahlEinwohner(land);
        int infizierte = coronaDaten.getAnzahlInfizierte(land);
        return (double) (infizierte * 100) / (double) einwohner;
    }

    @Override
    public double getTodesrate(String land) {
        int tote = coronaDaten.getAnzahlTote(land);
        int infizierte = coronaDaten.getAnzahlInfizierte(land);
        if (infizierte == 0) {
            return 0.0;
        } else {
            return (double) (tote * 100) / (double) infizierte;
        }
    }

    @Override
    public String getText(String land) {
        String result = String.format("Infizierte: %.4f%%, Todesrate: %.1f%%",
                getInfizierteProzent(land), getTodesrate(land));
        return result;
    }
}
