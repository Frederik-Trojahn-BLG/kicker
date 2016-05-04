import Ember from 'ember';
import OAuth2PasswordGrant from 'ember-simple-auth/authenticators/oauth2-password-grant';

export default OAuth2PasswordGrant.extend({
    serverTokenRevocationEndpoint: '/oauth/revoke',
    serverTokenEndpoint: '/oauth/token',

    makeRequest: function (url, data) {
        var client_id = 'kicker';
        var client_secret = 'secret';
        var headerAuth = "Basic " + btoa(client_id + ":" + client_secret);
        return Ember.$.ajax({
            url: this.serverTokenEndpoint,
            type: 'POST',
            data: data,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded',
            headers: {
                Authorization: headerAuth
            }
        });
    }
});
