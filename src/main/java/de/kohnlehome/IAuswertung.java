package de.kohnlehome;

public interface IAuswertung {
    double getInfizierteProzent(String land);
    double getTodesrate(String land);
    String getText(String land);
}
