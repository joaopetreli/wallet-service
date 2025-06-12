package com.petreli.application.usecases;

public abstract class UnitUseCase<INPUT> {

    /**
     * Use cases follows the above rules:
     * 1. Each use case has one Input.
     * 2. It does not return an entity, aggregate or value object.
     * 3. It must implement the Command pattern.
     */
    public abstract void execute(INPUT input);
}
