package ru.techcoredev.store.web.servlets;


public enum JSPPages {

    LOGIN {
        {
            this.url = "/WEB-INF/view/login.jsp";
        }
    },
    REGISTRATION {
        {
            this.url = "/WEB-INF/view/registration.jsp";
        }
    },
    ALL_USERS {
        {
            this.url = "/WEB-INF/view/viewAllUsers.jsp";
        }
    },
    ADMIN {
        {
            this.url = "/WEB-INF/view/adminMenu.jsp";
        }
    },
    USER_BY_ID {
        {
            this.url = "/WEB-INF/view/viewUserById.jsp";
        }
    },
    ORDERS {
        {
            this.url = "/WEB-INF/view/allOrders.jsp";
        }
    },
    ORDER_DETAILS {
        {
            this.url = "/WEB-INF/view/order.jsp";
        }
    },
    ADD_USER {
        {
            this.url = "/WEB-INF/view/addUser.jsp";
        }
    },

    SUCCESS {
        {
            this.url = "/WEB-INF/view/success.jsp";
        }
    };


    public String url;

    public String getUrl() {
        return this.url;
    }
}
