/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

    attributes: {
        nombre: {
            type: 'string'
        },
        usuario: { //RELACION DE UNO A MUCHOS (NOMBRE FK) -MISMO NOMBRE QUE LA RELACION
            model: 'usuario',
            required: true // (Es opcional 1 muchos 0 muchos)
        },
        batallas: {
            collection: 'batalla',
            via: 'pokemon',
        }
    },

};