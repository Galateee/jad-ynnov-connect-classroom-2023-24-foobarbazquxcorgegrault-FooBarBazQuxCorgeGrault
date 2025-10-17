package com.jad;

import java.util.ArrayList;
import java.util.List;

public class Foo {
    private final Bar bar;
    private final List<Baz> bazs = new ArrayList<>();
    private final Qux qux = new Qux();
    private final List<Grault> graults = new ArrayList<>();
    private Corge corge;

    public Foo(final Bar bar) {
        this.bar = bar;
    }

    public Bar getBar() {
        return this.bar;
    }

    public List<Baz> getBazs() {
        return this.bazs;
    }

    public void addBaz(final Baz baz) {
        if (baz != null) {
            this.bazs.add(baz);
        }
    }

    public Qux getQux() {
        return this.qux;
    }

    public List<Grault> getGraults() {
        return this.graults;
    }

    public void addGrault() {
        this.graults.add(new Grault(this));
    }

    public Corge getCorge() {
        return this.corge;
    }

    public void setCorge(final Corge newCorge) {
        if (this.corge == newCorge) {
            return;
        }
        Corge old = this.corge;
        this.corge = newCorge;
        if (old != null && old.getFoo() == this) {
            old.setFoo(null);
        }
        if (newCorge != null && newCorge.getFoo() != this) {
            newCorge.setFoo(this);
        }
    }
}
