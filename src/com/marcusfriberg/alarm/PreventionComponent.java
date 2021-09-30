package com.marcusfriberg.alarm;

// Interface for prevention components. Other common
// properties and methods for components are inherited
// from the abstract class AlarmComponent.
public interface PreventionComponent {
    void startPreventiveAction();
    void stopPreventiveAction();
}
