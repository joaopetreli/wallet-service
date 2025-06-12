package com.petreli.application.usecases;

public abstract class UseCase<INPUT, OUTPUT> {

    /**
     * Use cases follows the above rules:
     * 1. Each use case has one Input and one Output.
     * 2. It does not return an entity, aggregate or value object.
     * 3. It must implement the Command pattern.
     */
    public abstract OUTPUT execute(INPUT input);
}