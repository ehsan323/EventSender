package com.adjust.adjusthomework.view;

import com.adjust.adjusthomework.data.repository.AdjustSecondRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class MainPresenterTest {

    @Mock
    private MainContract.View view;

    @Mock
    private AdjustSecondRepository repository;


    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new MainPresenter(view);
    }

    @Test
    public void secondIsValidForSending_Test() {

        // GIVEN
        presenter.sentSeconds.add("50");

        // WHEN
        boolean isValid = presenter.secondIsValidForSending(52);

        // THEN
        Assert.assertFalse(isValid);
    }

    @Test
    public void sendSelectedSecond_Duplicate_Test() {

        // GIVEN
        presenter.sentSeconds.add("50");

        // WHEN
        presenter.sendSelectedSecond(50);

        // THEN
        Mockito.verify(view).showError("The Second has been sent before to server!");

    }


}