         while(l1!=null || l2!=null) {
            int sum = carry;
            if(l1!=null) sum +=l1.val;
            if(l2!=null) sum +=l2.val;
            ListNode newNode = new ListNode(sum%10);
            curr.next = newNode;
            curr = newNode;
            carry = sum/10;
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
         }
         if(carry!=0) {
            ListNode newNode = new ListNode(carry);
            curr.next = newNode;