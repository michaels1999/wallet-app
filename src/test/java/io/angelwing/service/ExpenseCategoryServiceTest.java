package io.angelwing.service;

import io.angelwing.model.ExpenseCategory;
import io.angelwing.repository.ExpenseCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.angelwing.service.generator.ExpenseCategoryGenerator.generateRandomExpenseCategories;
import static io.angelwing.service.generator.ExpenseCategoryGenerator.generateRandomExpenseCategoryWithId;
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

class ExpenseCategoryServiceTest {

    private final ExpenseCategoryRepository expenseCategoryRepository = mock(ExpenseCategoryRepository.class);

    private final ExpenseCategoryService expenseCategoryService = new ExpenseCategoryServiceImpl(expenseCategoryRepository);

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(expenseCategoryRepository);
    }

    @Test
    void shouldAddExpenseCategory() {
        // Data preparation
        final UUID expenseCategoryId = UUID.randomUUID();
        final ExpenseCategory expectedExpenseCategory = generateRandomExpenseCategoryWithId(expenseCategoryId);

        //Mock preparation
        when(expenseCategoryRepository.save(expectedExpenseCategory)).thenReturn(expectedExpenseCategory);
        when(expenseCategoryRepository.findById(expenseCategoryId)).thenReturn(Optional.of(expectedExpenseCategory));

        // Service execution
        expenseCategoryService.addExpenseCategory(expectedExpenseCategory);

        // Assertion of result
        final Optional<ExpenseCategory> actualExpenseCategory = expenseCategoryService.getExpenseCategoryById(expenseCategoryId);
        verify(expenseCategoryRepository).save(expectedExpenseCategory);
        verify(expenseCategoryRepository).findById(expenseCategoryId);

        assertTrue(actualExpenseCategory.isPresent());
        assertEquals(actualExpenseCategory.get(), expectedExpenseCategory);
    }

    @Test
    void shouldUpdateExpenseCategory() {
        // Data preparation
        final UUID expenseCategoryId = UUID.randomUUID();
        final ExpenseCategory expectedExpenseCategory = generateRandomExpenseCategoryWithId(expenseCategoryId);

        //Mock preparation
        when(expenseCategoryRepository.findById(expenseCategoryId)).thenReturn(Optional.of(expectedExpenseCategory));

        // Service execution
        expenseCategoryService.updateExpenseCategory(expectedExpenseCategory);

        // Assertion of result
        final Optional<ExpenseCategory> actualExpenseCategory = expenseCategoryService.getExpenseCategoryById(expenseCategoryId);
        verify(expenseCategoryRepository).save(expectedExpenseCategory);
        verify(expenseCategoryRepository).findById(expenseCategoryId);

        assertTrue(actualExpenseCategory.isPresent());
        assertEquals(actualExpenseCategory.get(), expectedExpenseCategory);
    }

    @Test
    void shouldRemoveExpenseCategory() {
        // Data preparation
        final UUID expenseCategoryId = UUID.randomUUID();
        final ExpenseCategory expectedExpenseCategory = generateRandomExpenseCategoryWithId(expenseCategoryId);

        // Mock preparation
        when(expenseCategoryRepository.findById(expenseCategoryId))
                .thenReturn(Optional.of(expectedExpenseCategory))
                .thenReturn(Optional.empty());
        doNothing().when(expenseCategoryRepository).deleteById(expenseCategoryId);

        // Service execution
        final Optional<ExpenseCategory> actualExpenseCategory = expenseCategoryService.getExpenseCategoryById(expenseCategoryId);
        expenseCategoryService.removeExpenseCategory(actualExpenseCategory.get().getId());

        // Assertion of result
        final Optional<ExpenseCategory> deletedExpenseCategory = expenseCategoryService.getExpenseCategoryById(expenseCategoryId);
        verify(expenseCategoryRepository, times(2)).findById(expenseCategoryId);
        verify(expenseCategoryRepository).deleteById(expenseCategoryId);

        assertFalse(deletedExpenseCategory.isPresent());
    }

    @Test
    void shouldTestListOfExpenseCategory() {
        // Data preparation
        final List<ExpenseCategory> expectedExpenseCategories = generateRandomExpenseCategories(5);

        // Mock preparation
        when(expenseCategoryRepository.findAll()).thenReturn(expectedExpenseCategories);

        // Service execution
        final List<ExpenseCategory> actualExpenseCategories = expenseCategoryService.listExpenseCategory();

        // Assertion of result
        verify(expenseCategoryRepository).findAll();

        assertEquals(expectedExpenseCategories, actualExpenseCategories);
    }

    @Test
    void shouldThrowExceptionOnAddExpenseCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseCategoryRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> expenseCategoryService.addExpenseCategory(null));

        verify(expenseCategoryRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnUpdateExpenseCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseCategoryRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> expenseCategoryService.updateExpenseCategory(null));

        verify(expenseCategoryRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnRemoveExpenseCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(expenseCategoryRepository).deleteById(null);

        assertThrows(IllegalArgumentException.class, () -> expenseCategoryService.removeExpenseCategory(null));

        verify(expenseCategoryRepository).deleteById(null);
    }
}