package io.angelwing.service;

import io.angelwing.model.IncomeCategory;
import io.angelwing.repository.IncomeCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.angelwing.service.generator.IncomeCategoryGenerator.generateRandomIncomeCategory;
import static io.angelwing.service.generator.IncomeCategoryGenerator.generateRandomIncomeWithId;
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

class IncomeCategoryServiceTest {

    private final IncomeCategoryRepository incomeCategoryRepository = mock(IncomeCategoryRepository.class);

    private final IncomeCategoryService incomeCategoryService = new IncomeCategoryServiceImpl(incomeCategoryRepository);


    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(incomeCategoryRepository);
    }

    @Test
    void shouldAddExpenseCategory() {
        // Data preparation
        final UUID incomeCategoryId = UUID.randomUUID();
        final IncomeCategory expectedIncomeCategory = generateRandomIncomeWithId(incomeCategoryId);

        //Mock preparation
        when(incomeCategoryRepository.save(expectedIncomeCategory)).thenReturn(expectedIncomeCategory);
        when(incomeCategoryRepository.findById(incomeCategoryId)).thenReturn(Optional.of(expectedIncomeCategory));

        // Service execution
        incomeCategoryService.addIncomeCategory(expectedIncomeCategory);

        // Assertion of result
        final Optional<IncomeCategory> actualIncomeCategory = incomeCategoryService.getIncomeCategoryById(incomeCategoryId);
        verify(incomeCategoryRepository).save(expectedIncomeCategory);
        verify(incomeCategoryRepository).findById(incomeCategoryId);

        assertTrue(actualIncomeCategory.isPresent());
        assertEquals(actualIncomeCategory.get(), expectedIncomeCategory);
    }

    @Test
    void shouldUpdateExpenseCategory() {
        // Data preparation
        final UUID incomeCategoryId = UUID.randomUUID();
        final IncomeCategory expectedIncomeCategory = generateRandomIncomeWithId(incomeCategoryId);

        //Mock preparation
        when(incomeCategoryRepository.findById(incomeCategoryId)).thenReturn(Optional.of(expectedIncomeCategory));

        // Service execution
        incomeCategoryService.updateIncomeCategory(expectedIncomeCategory);

        // Assertion of result
        final Optional<IncomeCategory> actualIncomeCategory = incomeCategoryService.getIncomeCategoryById(incomeCategoryId);
        verify(incomeCategoryRepository).save(expectedIncomeCategory);
        verify(incomeCategoryRepository).findById(incomeCategoryId);

        assertTrue(actualIncomeCategory.isPresent());
        assertEquals(actualIncomeCategory.get(), expectedIncomeCategory);
    }

    @Test
    void shouldRemoveExpenseCategory() {
        // Data preparation
        final UUID incomeCategoryId = UUID.randomUUID();
        final IncomeCategory expectedIncomeCategory = generateRandomIncomeWithId(incomeCategoryId);

        // Mock preparation
        when(incomeCategoryRepository.findById(incomeCategoryId))
                .thenReturn(Optional.of(expectedIncomeCategory))
                .thenReturn(Optional.empty());
        doNothing().when(incomeCategoryRepository).deleteById(incomeCategoryId);

        // Service execution
        final Optional<IncomeCategory> actualIncomeCategory = incomeCategoryService.getIncomeCategoryById(incomeCategoryId);
        incomeCategoryService.removeIncomeCategory(actualIncomeCategory.get().getId());

        // Assertion of result
        final Optional<IncomeCategory> deletedIncomeCategory = incomeCategoryService.getIncomeCategoryById(incomeCategoryId);
        verify(incomeCategoryRepository, times(2)).findById(incomeCategoryId);
        verify(incomeCategoryRepository).deleteById(incomeCategoryId);

        assertFalse(deletedIncomeCategory.isPresent());
    }

    @Test
    void shouldTestListOfExpenseCategory() {
        // Data preparation
        final List<IncomeCategory> incomeCategories = generateRandomIncomeCategory(5);

        // Mock preparation
        when(incomeCategoryRepository.findAll()).thenReturn(incomeCategories);

        // Service execution
        List<IncomeCategory> actualIncomeCategories = incomeCategoryService.listIncomeCategory();

        // Assertion of result
        verify(incomeCategoryRepository).findAll();

        assertEquals(incomeCategories, actualIncomeCategories);
    }

    @Test
    void shouldThrowExceptionOnAddIncomeCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeCategoryRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> incomeCategoryService.addIncomeCategory(null));

        verify(incomeCategoryRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnUpdateIncomeCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeCategoryRepository).save(null);

        assertThrows(IllegalArgumentException.class, () -> incomeCategoryService.updateIncomeCategory(null));

        verify(incomeCategoryRepository).save(null);
    }

    @Test
    void shouldThrowExceptionOnRemoveIncomeCategoryWhenExpenseIsNull() {

        doThrow(IllegalArgumentException.class).when(incomeCategoryRepository).deleteById(null);

        assertThrows(IllegalArgumentException.class, () -> incomeCategoryService.removeIncomeCategory(null));

        verify(incomeCategoryRepository).deleteById(null);
    }
}
