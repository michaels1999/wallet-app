package io.angelwing.service;

import io.angelwing.model.Income;
import io.angelwing.repository.IncomeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.angelwing.service.generator.IncomeGenerator.generateRandomIncome;
import static io.angelwing.service.generator.IncomeGenerator.generateRandomIncomeWithId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class IncomeServiceTest {

    private final IncomeRepository incomeRepository = mock(IncomeRepository.class);

    private final IncomeService incomeService = new IncomeServiceImpl(incomeRepository);

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(incomeRepository);
    }

    @Test
    void shouldAddIncome() {
        // Data preparation
        final UUID incomeId = UUID.randomUUID();
        final Income expectedIncome = generateRandomIncomeWithId(incomeId);

        // Mock preparation
        when(incomeRepository.save(expectedIncome)).thenReturn(expectedIncome);
        when(incomeRepository.findById(incomeId)).thenReturn(Optional.of(expectedIncome));

        // Service execution
        incomeService.addIncome(expectedIncome);

        // Assertion of result
        final Optional<Income> actualIncome = incomeService.getIncomeById(incomeId);
        verify(incomeRepository).save(expectedIncome);
        verify(incomeRepository).findById(incomeId);

        assertTrue(actualIncome.isPresent());
        assertEquals(actualIncome.get(), expectedIncome);
    }

    @Test
    void shouldUpdateIncome() {
        // Data preparation
        final UUID incomeId = UUID.randomUUID();
        final Income expectedIncome = generateRandomIncomeWithId(incomeId);

        // Mock preparation
        when(incomeRepository.findById(incomeId)).thenReturn(Optional.of(expectedIncome));

        // Service execution
        incomeService.updateIncome(expectedIncome);

        // Assertion of result
        final Optional<Income> actualIncome = incomeService.getIncomeById(incomeId);
        verify(incomeRepository).save(expectedIncome);
        verify(incomeRepository).findById(incomeId);

        assertTrue(actualIncome.isPresent());
        assertEquals(actualIncome.get(), expectedIncome);
    }

    @Test
    void shouldDeleteIncome() {
        // Data preparation
        final UUID incomeId = UUID.randomUUID();
        final Income expectedIncome = generateRandomIncomeWithId(incomeId);

        // Mock preparation
        when(incomeRepository.findById(incomeId))
                .thenReturn(Optional.of(expectedIncome))
                .thenReturn(Optional.empty());
        doNothing().when(incomeRepository).deleteById(incomeId);

        // Service execution
        final Optional<Income> actualIncome = incomeService.getIncomeById(incomeId);
        incomeService.removeIncome(actualIncome.get().getId());

        // Assertion of result
        final Optional<Income> deletedIncome = incomeService.getIncomeById(incomeId);
        verify(incomeRepository, times(2)).findById(incomeId);
        verify(incomeRepository).deleteById(incomeId);

        assertFalse(deletedIncome.isPresent());

    }

    @Test
    void shouldTestListOfIncome() {
        // Data preparation
        final List<Income> expectedIncome = generateRandomIncome(5);

        // Mock preparation
        when(incomeRepository.findAll()).thenReturn(expectedIncome);

        // Service execution
        List<Income> actualIncomes = incomeService.listIncome();

        // Assertion of result
        verify(incomeRepository).findAll();

        assertEquals(expectedIncome, actualIncomes);
    }

    @Test
    void shouldThrowExceptionOnAddIncomeWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> incomeService.addIncome(null));

        verify(incomeRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnUpdateIncomeWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> incomeService.updateIncome(null));

        verify(incomeRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnRemoveIncomeWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeRepository).deleteById(null);

        assertThrows(IllegalArgumentException.class, () -> incomeService.removeIncome(null));

        verify(incomeRepository).deleteById(null);
    }
}