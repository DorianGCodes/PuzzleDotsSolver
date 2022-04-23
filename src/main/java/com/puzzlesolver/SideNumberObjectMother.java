package com.puzzlesolver;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

class SideNumberObjectMother {
    static Set<SideNumberIndicator> generateExampleWallIndicators(Map<Point,Node> nodesByPoint) {
        var indicator04 = new SideNumberIndicator(0.5,4.5,3, nodesByPoint);
        var indicator14 = new SideNumberIndicator(1.5,4.5,99, nodesByPoint);
        var indicator24 = new SideNumberIndicator(2.5,4.5,3, nodesByPoint);
        var indicator34 = new SideNumberIndicator(3.5,4.5,3, nodesByPoint);
        var indicator44 = new SideNumberIndicator(4.5,4.5,3, nodesByPoint);

        var indicator03 = new SideNumberIndicator(0.5,3.5,1, nodesByPoint);
        var indicator13 = new SideNumberIndicator(1.5,3.5,2, nodesByPoint);
        var indicator23 = new SideNumberIndicator(2.5,3.5,99, nodesByPoint);
        var indicator33 = new SideNumberIndicator(3.5,3.5,2, nodesByPoint);
        var indicator43 = new SideNumberIndicator(4.5,3.5,1, nodesByPoint);

        var indicator02 = new SideNumberIndicator(0.5,2.5,99, nodesByPoint);
        var indicator12 = new SideNumberIndicator(1.5,2.5,2, nodesByPoint);
        var indicator22 = new SideNumberIndicator(2.5,2.5,99, nodesByPoint);
        var indicator32 = new SideNumberIndicator(3.5,2.5,99, nodesByPoint);
        var indicator42 = new SideNumberIndicator(4.5,2.5,99, nodesByPoint);

        var indicator01 = new SideNumberIndicator(0.5,1.5,2, nodesByPoint);
        var indicator11 = new SideNumberIndicator(1.5,1.5,3, nodesByPoint);
        var indicator21 = new SideNumberIndicator(2.5,1.5,3, nodesByPoint);
        var indicator31 = new SideNumberIndicator(3.5,1.5,2, nodesByPoint);
        var indicator41 = new SideNumberIndicator(4.5,1.5,2, nodesByPoint);


        var indicator00 = new SideNumberIndicator(0.5,0.5,2, nodesByPoint);
        var indicator10 = new SideNumberIndicator(1.5,0.5,99, nodesByPoint);
        var indicator20 = new SideNumberIndicator(2.5,0.5,2, nodesByPoint);
        var indicator30 = new SideNumberIndicator(3.5,0.5,2, nodesByPoint);
        var indicator40 = new SideNumberIndicator(4.5,0.5,3, nodesByPoint);
        return ImmutableSet.<SideNumberIndicator>builder()
                .add(
                        indicator00,
                        indicator10,
                        indicator20,
                        indicator30,
                        indicator40,
                        indicator02,
                        indicator12,
                        indicator22,
                        indicator32,
                        indicator42,
                        indicator01,
                        indicator11,
                        indicator21,
                        indicator31,
                        indicator41,
                        indicator04,
                        indicator14,
                        indicator24,
                        indicator34,
                        indicator44,
                        indicator03,
                        indicator13,
                        indicator23,
                        indicator33,
                        indicator43
                )
                .build();
    }
}
