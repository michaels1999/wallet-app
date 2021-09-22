package io.angelwing.service;

import io.angelwing.model.Expense;
import io.angelwing.repository.ExpenseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.angelwing.service.generator.ExpenseGenerator.generateRandomExpenseWithId;
import static io.angelwing.service.generator.ExpenseGenerator.generateRandomExpenses;
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

class ExpenseServiceTest {

    private final ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

    private final ExpenseService expenseService = new ExpenseServiceImpl(expenseRepository);

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(expenseRepository);
    }

    @Test
    void shouldAddExpense() {
        // Data preparation
        final UUID expenseId = UUID.randomUUID();
        final Expense expectedExpense = generateRandomExpenseWithId(expenseId);

        // Mock preparation
        when(expenseRepository.save(expectedExpense)).thenReturn(expectedExpense);
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(expectedExpense));

        // Service execution
        expenseService.addExpense(expectedExpense);

        // Assertions of result
        final Optional<Expense> actualExpense = expenseService.getExpenseById(expenseId);
        verify(expenseRepository).save(expectedExpense);
        verify(expenseRepository).findById(expenseId);

        assertTrue(actualExpense.isPresent());
        assertEquals(actualExpense.get(), expectedExpense);
    }

    @Test
    void shouldUpdateExpense() {
        // Data preparation
        final UUID expenseId = UUID.randomUUID();
        final Expense expectedExpense = generateRandomExpenseWithId(expenseId);

        // Mock preparation
        when(expenseRepository.save(expectedExpense)).thenReturn(expectedExpense);
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(expectedExpense));

        // Service execution
        expenseService.updateExpense(expectedExpense);

        // Assertions of result
        final Optional<Expense> actualExpense = expenseService.getExpenseById(expenseId);
        verify(expenseRepository).save(expectedExpense);
        verify(expenseRepository).findById(expenseId);

        assertTrue(actualExpense.isPresent());
        assertEquals(actualExpense.get(), expectedExpense);
    }

    @Test
    void shouldRemoveExpense() {
        // Data preparation
        final UUID expenseId = UUID.randomUUID();
        final Expense expectedExpense = generateRandomExpenseWithId(expenseId);

        // Mock preparation
        when(expenseRepository.findById(expenseId))
                .thenReturn(Optional.of(expectedExpense))
                .thenReturn(Optional.empty());
        doNothing().when(expenseRepository).deleteById(expenseId);

        // Service execution
        final Optional<Expense> actualExpense = expenseService.getExpenseById(expenseId);
        expenseService.removeExpense(actualExpense.get().getId());

        // Assertion of result
        final Optional<Expense> deletedExpense = expenseService.getExpenseById(expenseId);
        verify(expenseRepository, times(2)).findById(expenseId);
        verify(expenseRepository).deleteById(expenseId);

        assertFalse(deletedExpense.isPresent());
    }

    @Test
    void shouldTestListOfExpense() {
        // Data preparation
        final List<Expense> expectedExpenses = generateRandomExpenses(5);

        // Mock preparation
        when(expenseRepository.findAll()).thenReturn(expectedExpenses);

        // Service execution
        List<Expense> actualExpenses = expenseService.listExpense();

        // Assertion of result
        verify(expenseRepository).findAll();

        assertEquals(expectedExpenses, actualExpenses);
    }

    @Test
    void shouldThrowExceptionOnAddExpenseWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> expenseService.addExpense(null));

        verify(expenseRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnUpdateExpenseWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> expenseService.updateExpense(null));

        verify(expenseRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnRemoveExpenseWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseRepository).deleteById(null);

        assertThrows(IllegalArgumentException.class, () -> expenseService.removeExpense(null));

        verify(expenseRepository).deleteById(null);
    }

}
