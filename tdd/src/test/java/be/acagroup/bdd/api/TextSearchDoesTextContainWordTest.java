package be.acagroup.bdd.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextSearchDoesTextContainWordTest {

    @Test
    public void givenAText_whenTheTextContainsTheGivenWord_thenReturnsTrue() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "test");

        assertThat(containsTheWord).isTrue();
    }

    @Test
    public void givenAText_whenTheTextContainsTheGivenWordIgnoringCaps_thenReturnsTrue() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "TeSt");

        assertThat(containsTheWord).isTrue();
    }

    @Test
    public void givenAText_whenTheTextDoesNotContainTheGivenWord_thenReturnsFalse() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "notfound");

        assertThat(containsTheWord).isFalse();
    }

    @Test
    public void givenAText_whenTheTextDoesNotCompletelyContainTheGivenWord_thenReturnsFalse() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "thiw");

        assertThat(containsTheWord).isFalse();
    }

    @Test
    public void givenAText_whenTheTextContainsTheGivenWordMultipleTimes_thenReturnsTrue() {
        String text = "This test is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "test");

        assertThat(containsTheWord).isTrue();
    }

    @Disabled
    @Test
    public void givenAnEmptyText_whenValidatingAWordAgainstTheEmptyText_thenReturnsFalse() {
        String text = "";

        boolean containsTheWord = StringHelperUtil.is(text, "test");

        assertThat(containsTheWord).isFalse();
    }

    @Disabled
    @Test
    public void givenAText_whenValidatingAnEmptyWord_thenReturnsTrue() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, "");

        assertThat(containsTheWord).isTrue();
    }

    @Disabled
    @Test
    public void givenAnEmptyText_whenValidatingAnEmptyWord_thenReturnsTrue() {
        String text = "";

        boolean containsTheWord = StringHelperUtil.is(text, "");

        assertThat(containsTheWord).isTrue();
    }

    @Disabled
    @Test
    public void givenTextIsNull_whenValidatingAWordAgainstTheNullText_thenReturnsFalse() {
        String text = null;

        boolean containsTheWord = StringHelperUtil.is(text, "test");

        assertThat(containsTheWord).isFalse();
    }

    @Disabled
    @Test
    public void givenAText_whenValidatingNullAgainstTheText_thenReturnsFalse() {
        String text = "This is a test text";

        boolean containsTheWord = StringHelperUtil.is(text, null);

        assertThat(containsTheWord).isFalse();
    }
}
