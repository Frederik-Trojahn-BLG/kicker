package de.almostintelligent.kicker.controller;

import de.almostintelligent.kicker.service.ConstructibleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(value = MockitoJUnitRunner.class)
public class ConstructibleControllerTest {

    @Mock
    private ConstructibleService constructibleService;

    @InjectMocks
    private ConstructibleController constructibleController;

    @Test
    public void testGetBuildings() throws Exception {
        constructibleController.getConstructibles();
        verify(constructibleService, times(1)).getBuildingListFromFocusedVillage();
    }

    @Test
    public void testGetResearches() throws Exception {
        constructibleController.getConstructibles();
        verify(constructibleService, times(1)).getResearchesFromPlayer();
    }

    @Test
    public void testGetUnits() throws Exception {
        constructibleController.getConstructibles();
        verify(constructibleService, times(1)).getUnitListFromFocusedVillage();
    }

    @Test(expected = Exception.class)
    public void testWrongType() throws Exception {
        constructibleController.getConstructibles();
    }

}
