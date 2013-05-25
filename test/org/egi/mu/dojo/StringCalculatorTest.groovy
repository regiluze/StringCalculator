package org.egi.mu.dojo

import org.junit.Test

import static org.egi.mu.dojo.Calculator.*


class StringCalculatorTest {


    @Test
    public void should_return_zero_when_input_string_is_empty(){

        assert 0 == add("")
    }

    @Test
    public void should_return_the_same_number_when_the_input_is_one_number(){

        assert 5 == add(5.toString())

    }

    @Test
    public void should_return_the_add_when_the_input_are_two_numbers(){

        assert 10 == add("5,5")

    }

    @Test
    public void should_return_the_add_when_input_are_two_numbers_and_allow_new_line_splitter(){

        assert 6 == add("1\\n2,3")

    }

    @Test
    public void should_return_the_add_with_different_splitter(){

        assert 3 == add("//;\\n1;2")

    }

    @Test(expected = Exception.class)
    public void should_throw_a_exception_when_there_are_negative_numbers(){

        add("//;\\n1;-2")

    }

    @Test
    public void should_contains_negative_numbers_in_exception_msg_when_throw_exception(){

        try {
            add("//;\\n1;-2;-3")
        }catch (Exception e){
            String msg = e.getMessage()
            assert msg == "[-2, -3]"
        }

    }

    @Test
    public void should_ignore_numbers_bigger_then_1000(){

        assert 3 == add("//;\\n1;2;1001")

    }

    @Test
    public void should_allow_any_size_splitter(){

        assert 6 == add("//[***]\\n1***2***3")

    }

    @Test
    public void should_allow_multiple_splitter(){

        assert 6 == add("//[*][%]\\n1*2%3")

    }


}
