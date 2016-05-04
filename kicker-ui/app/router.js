import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('login');
  this.route('kicker', function() {
    this.route('dashboard');
    this.route('my-teams');
    this.route('my-leagues');
    this.route('score-boards');
  });
});

export default Router;
