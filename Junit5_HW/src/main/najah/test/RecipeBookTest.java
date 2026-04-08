package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("RecipeBook Tests")
public class RecipeBookTest {

    RecipeBook book;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        book = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Coffee");
    }

    @Test
    @DisplayName("Test adding recipe")
    void testAddRecipe() {
        assertTrue(book.addRecipe(recipe));
        assertNotNull(book.getRecipes()[0]);
    }

    @Test
    @DisplayName("Test adding duplicate recipe")
    void testAddDuplicateRecipe() {
        book.addRecipe(recipe);
        assertFalse(book.addRecipe(recipe));
    }

    @Test
    @DisplayName("Test deleting recipe")
    void testDeleteRecipe() {
        book.addRecipe(recipe);
        String name = book.deleteRecipe(0);

        assertEquals("Coffee", name);
        assertNotNull(book.getRecipes()[0]);
    }

    @Test
    @DisplayName("Test deleting non-existing recipe")
    void testDeleteInvalid() {
        assertNull(book.deleteRecipe(0));
    }

    @Test
    @DisplayName("Test editing recipe")
    void testEditRecipe() {
        Recipe r = new Recipe();
        r.setName("Coffee");

        book.addRecipe(r);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Tea");

        String oldName = book.editRecipe(0, newRecipe);

        assertEquals("Coffee", oldName);
    }

    @Test
    @DisplayName("Test editing non-existing recipe")
    void testEditInvalid() {
        Recipe newRecipe = new Recipe();
        assertNull(book.editRecipe(0, newRecipe));
    }

    @Test
    @DisplayName("Test full recipe book")
    void testFullRecipeBook() {
        for (int i = 0; i < 4; i++) {
            Recipe r = new Recipe();
            r.setName("R" + i);
            assertTrue(book.addRecipe(r));
        }

        Recipe extra = new Recipe();
        extra.setName("Extra");

        assertFalse(book.addRecipe(extra));
    }

    @Test
    @Timeout(2)
    @DisplayName("Timeout test")
    void testTimeout() {
        book.getRecipes();
    }
    
    @Test
    @DisplayName("Test add null recipe")
    void testAddNullRecipe() {
        assertThrows(NullPointerException.class, () -> {
            book.addRecipe(null);
        });
    }

    @Test
    @DisplayName("Test delete after delete")
    void testDeleteTwice() {
        book.addRecipe(recipe);
        book.deleteRecipe(0);

        assertNotNull(book.deleteRecipe(0)); 
    }

    @Test
    @DisplayName("Test edit after delete")
    void testEditAfterDelete() {
        book.addRecipe(recipe);
        book.deleteRecipe(0);

        Recipe newRecipe = new Recipe();
        assertNotNull(book.editRecipe(0, newRecipe));
    }
    
    @Test
    @DisplayName("Test getRecipes initial state")
    void testGetRecipesInitial() {
        Recipe[] recipes = book.getRecipes();

        assertEquals(4, recipes.length);
        for (Recipe r : recipes) {
            assertNull(r);
        }
    }
    
    @Test
    @DisplayName("Test adding multiple recipes positions")
    void testMultipleRecipesPositions() {
        Recipe r1 = new Recipe();
        r1.setName("A");

        Recipe r2 = new Recipe();
        r2.setName("B");

        book.addRecipe(r1);
        book.addRecipe(r2);

        assertEquals("A", book.getRecipes()[0].getName());
        assertEquals("B", book.getRecipes()[1].getName());
    }
    
    @Test
    @DisplayName("Test adding same content different object")
    void testDuplicateDifferentObject() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");

        Recipe r2 = new Recipe();
        r2.setName("Coffee");

        book.addRecipe(r1);
        boolean result = book.addRecipe(r2);

        assertFalse(result);
    }
    
    @Test
    @DisplayName("Test edit replaces recipe")
    void testEditReplace() {
        Recipe r = new Recipe();
        r.setName("Old");

        book.addRecipe(r);

        Recipe newR = new Recipe();
        newR.setName("New");

        book.editRecipe(0, newR);

        assertEquals("", book.getRecipes()[0].getName());
    }
    
    @Test
    @DisplayName("Test delete from second position")
    void testDeleteSecondPosition() {
        Recipe r1 = new Recipe(); r1.setName("A");
        Recipe r2 = new Recipe(); r2.setName("B");

        book.addRecipe(r1);
        book.addRecipe(r2);

        String name = book.deleteRecipe(1);

        assertEquals("B", name);
    }

    @Test
    @DisplayName("Test edit second position")
    void testEditSecondPosition() {
        Recipe r1 = new Recipe(); r1.setName("A");
        Recipe r2 = new Recipe(); r2.setName("B");

        book.addRecipe(r1);
        book.addRecipe(r2);

        Recipe newR = new Recipe();
        newR.setName("C");

        String old = book.editRecipe(1, newR);

        assertEquals("B", old);
    }
    
    @Test
    @DisplayName("Test recipe name setters/getters")
    void testRecipeNameDirect() {
        Recipe r = new Recipe();
        r.setName("Espresso");

        assertEquals("Espresso", r.getName());
    }
    
    @Test
    @DisplayName("Test recipe equals")
    void testRecipeEquals() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");

        Recipe r2 = new Recipe();
        r2.setName("Coffee");

        assertTrue(r1.equals(r2));

    }
    
    @Test
    @DisplayName("Test recipe equals null")
    void testRecipeEqualsNull() {
        Recipe r = new Recipe();
        assertFalse(r.equals(null));
    }

    @Test
    @DisplayName("Test adding recipe with same name different object")
    void testAddSameNameDifferentObject() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");

        Recipe r2 = new Recipe();
        r2.setName("Coffee");

        book.addRecipe(r1);

        assertFalse(book.addRecipe(r2));
    }


    @Test
    @DisplayName("Test delete after replacing with empty recipe")
    void testDeleteAfterReplace() {
        book.addRecipe(recipe);

        book.deleteRecipe(0); 

        String result = book.deleteRecipe(0);

        assertEquals("", result); 
    }
    
    @Test
    @DisplayName("Test add recipe when array has nulls in between")
    void testAddWithNullGaps() {
        Recipe r1 = new Recipe();
        r1.setName("A");

        Recipe r2 = new Recipe();
        r2.setName("B");

        book.addRecipe(r1);
        book.addRecipe(r2);

        book.deleteRecipe(0);   

        Recipe r3 = new Recipe();
        r3.setName("C");

        assertTrue(book.addRecipe(r3));
    }
    
    @Test
    @DisplayName("Test edit on empty recipe after delete")
    void testEditAfterEmptyRecipe() {
        book.addRecipe(recipe);

        book.deleteRecipe(0); 

        Recipe newRecipe = new Recipe();
        newRecipe.setName("New");

        String result = book.editRecipe(0, newRecipe);

        assertEquals("", result); 
    }




    @Test
    @Disabled("Failing test example")
    @DisplayName("Disabled failing test")
    void failingTest() {
        assertEquals(5, book.getRecipes().length);
    }
}
