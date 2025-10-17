package com.jad;
public class Corge {
    private Foo foo;

    public Corge(Foo foo) {
        this.foo = null;
        if (foo != null && foo.getCorge() != this) {
            foo.setCorge(this);
        }
    }

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo newFoo) {
        if (this.foo == newFoo) {
            return;
        }
        this.foo = newFoo;
        if (newFoo != null && newFoo.getCorge() != this) {
            newFoo.setCorge(this);
        }
    }
}