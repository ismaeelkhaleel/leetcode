    l1, ListNode l2) {
         int carry = 0;
         ListNode dummy = new ListNode(-1);
         ListNode curr = dummy;
         while(l1!=null || l2!=null) {
            int sum = carry;
            if(l1!=null) sum +=l1.val;
            if(l2!=null) sum +=l2.val;