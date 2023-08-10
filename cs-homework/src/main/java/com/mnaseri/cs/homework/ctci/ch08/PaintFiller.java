package com.mnaseri.cs.homework.ctci.ch08;

public class PaintFiller {

    public static void main(String[] args) {
        Color[][] screen = {{Color.White, Color.White}, {Color.Green, Color.White}};
        Color[][] result = paint(screen, new Point(0, 0), Color.Black, Color.White);
        for (Color[] item : result) {
            for (Color i : item) {
                System.out.println("i = " + i);
            }
        }
    }

    public static Color[][] paint(Color[][] screen, Point p, Color newColor, Color currentColor) {
        if (!isInScreen(p, screen)) {
            return screen;
        }
        if (currentColor == screen[p.getX()][p.getY()]) {
            screen[p.getX()][p.getY()] = newColor;
            paint(screen, Point.top(p), newColor, currentColor);
            paint(screen, Point.bottom(p), newColor, currentColor);
            paint(screen, Point.left(p), newColor, currentColor);
            paint(screen, Point.right(p), newColor, currentColor);
        }
        return screen;
    }

    private static boolean isInScreen(Point p, Color[][] screen) {
        int width = screen.length;
        int height = screen[0].length;
        return p.getX() >= 0 && p.getY() >= 0 && p.getX() < width && p.getY() < height;
    }

    private enum Color {Yellow, Green, Black, White}

    public static class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Point top(Point p) {
            return new Point(p.getX() - 1, p.getY());
        }

        public static Point bottom(Point p) {
            return new Point(p.getX() + 1, p.getY());
        }
        //setters

        public static Point left(Point p) {
            return new Point(p.getX(), p.getY() - 1);
        }

        public static Point right(Point p) {
            return new Point(p.getX(), p.getY() + 1);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}