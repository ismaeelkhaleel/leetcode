        for(int i=2; i<=n; i++) {
            res = m1 + m2;
            m1 = m2;
            m2= res;
        }
        return m2;
    }
}