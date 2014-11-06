package fr.inria.diverse.noveltytesting.samples;

class FunctionsImpl implements Functions  {

    public function new() {}

    public function sum(a: Int, b: Int): Int {
        return a+b;
    }

    public function inverse(b: Bool): Bool {
        return !b;
    }

    public function echo(say: String): String {
        return say;
    }

    public function concat(a: String, b: String): String {
        return a+b;
    }
}


