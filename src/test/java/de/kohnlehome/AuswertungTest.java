package de.kohnlehome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuswertungTest {
    private IAuswertung auswertung;

    @BeforeEach
    public void init(){
        // ===== 1. ARRANGE =====
        // Testdoubles erstellen
        ICoronaDaten coronaDaten = mock(ICoronaDaten.class);

        when(coronaDaten.getAnzahlInfizierte("Iran")).thenReturn(8100);
        when(coronaDaten.getAnzahlInfizierte("Frankreich")).thenReturn(1700);
        when(coronaDaten.getAnzahlInfizierte("Nullland")).thenReturn(0);

        when(coronaDaten.getAnzahlTote("Iran")).thenReturn(291);
        when(coronaDaten.getAnzahlTote("Frankreich")).thenReturn(30);
        when(coronaDaten.getAnzahlTote("Nullland")).thenReturn(0);

        IEinwohnerDaten einwohnerDaten = mock(IEinwohnerDaten.class);

        when(einwohnerDaten.getAnzahlEinwohner("Iran")).thenReturn(81000000);
        when(einwohnerDaten.getAnzahlEinwohner("Frankreich")).thenReturn(66000000);
        when(einwohnerDaten.getAnzahlEinwohner("Nullland")).thenReturn(200);

        // Instanz der zu testenden Klasse erstellen
        // (benötigt Instanzen von ICoronaDaten und IEinwohnerDaten)
        auswertung = new Auswertung(coronaDaten, einwohnerDaten);
    }

    @Test
    public void test_infizierte_iran_0_01_Prozent(){
        // ===== 2. ACT =====
        // zu testende Methode der zu testenden Klasse aufrufen
        double result = auswertung.getInfizierteProzent("Iran");

        // ===== 3. ASSERT =====
        // überprüfen, ob Ergbnis mit dem erwarteten Wert übereinstimmt
        assertThat(result).isBetween(0.00995, 0.01005);
    }

    @Test
    public void test_infizierte_frankreich_0_0026_Prozent(){
        double result = auswertung.getInfizierteProzent("Frankreich");
        assertThat(result).isBetween(0.00255, 0.00265);
    }

    @Test
    public void test_infizierte_nulland_0_00_Prozent(){
        double result = auswertung.getInfizierteProzent("Nullland");
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void test_todesrate_iran_0_00_Prozent(){
        double result = auswertung.getTodesrate("Iran");
        assertThat(result).isBetween(3.55, 3.65);
    }

    @Test
    public void test_todesrate_frankreich_1_8_Prozent(){
        double result = auswertung.getTodesrate("Frankreich");
        assertThat(result).isBetween(1.75, 1.85);
    }

    @Test
    public void test_todesrate_nullland_0_00_Prozent(){
        double result = auswertung.getTodesrate("Nullland");
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void test_text_iran(){
        String result = auswertung.getText("Iran");
        assertThat(result).isEqualTo("Infizierte: 0,0100%, Todesrate: 3,6%");
    }
    @Test
    public void test_text_frankreich(){
        String result = auswertung.getText("Frankreich");
        assertThat(result).isEqualTo("Infizierte: 0,0026%, Todesrate: 1,8%");
    }
    @Test
    public void test_text_nullland(){
        String result = auswertung.getText("Nullland");
        assertThat(result).isEqualTo("Infizierte: 0,0000%, Todesrate: 0,0%");
    }
}
