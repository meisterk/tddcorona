package de.kohnlehome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AppTest {
    private MyInterface myInterface;

    @BeforeEach
    public void init(){
        // ===== ARRANGE =====
        // Testdouble erstellen
        myInterface = mock(MyInterface.class);

        // Verhalten des Testdoubles definieren
        when(myInterface.method(3)).thenReturn(7);
    }

    @Test
    public void test(){
        // ===== ACT =====
        // zu testende Methode aufrufen
        int x = myInterface.method(3);

        // ===== ASSERT =====
        // Überprüfen, ob Methode richtigen Wert zurückgibt
        assertThat(x).isEqualTo(7);

        // Überprüfen, ob Methoden des Testdoubles aufgerufen wurden
        verify(myInterface).method(3);
    }
}