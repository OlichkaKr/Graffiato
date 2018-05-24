from flask import Flask, jsonify, abort, make_response, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:olichka121@localhost:3306/iot-test-db'
db = SQLAlchemy(app)


class Plane(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(45))
    type = db.Column(db.String(45))
    capacity = db.Column(db.Integer)


@app.route('/planes', methods=['GET'])
def get_all_planes():
    planes = []
    all_planes = Plane.query.all()
    for pl in all_planes:
        plane = {
            'name': pl.name,
            'type': pl.type,
            'capacity': pl.capacity
        }
        planes.append(plane)
    db.session.commit()
    return jsonify({'planes': planes})


@app.route('/planes/<int:plane_id>', methods=['GET'])
def get_plane(plane_id):
    pl = Plane.query.filter_by(id=plane_id).first()
    plane = {
        'name': pl.name,
        'type': pl.type,
        'capacity': pl.capacity
    }
    db.session.commit()
    return jsonify({'plane': plane})


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)


@app.route('/planes', methods=['POST'])
def add_plane():
    if not request.json or not 'name' in request.json:
        abort(400)
    new_plane = Plane()
    new_plane.iD = request.json['id']
    new_plane.name = request.json['name']
    new_plane.type = request.json.get('type', "")
    new_plane.capacity = request.json.get('capacity', 0)

    db.session.add(new_plane)
    db.session.commit()
    return jsonify('Successful')


@app.route('/planes/<int:plane_id>', methods=['PUT'])
def update_plane(plane_id):
    plane = Plane.query.get(plane_id)

    plane.name = request.json['name']
    plane.type = request.json['type']
    plane.capacity = request.json.get('capacity', plane.capacity)
    db.session.commit()
    return jsonify('Successful')


@app.route('/planes/<int:plane_id>', methods=['DELETE'])
def delete_plane(plane_id):
    pl = Plane.query.filter_by(id=plane_id).first()
    db.session.delete(pl)
    db.session.commit()
    return jsonify({'result': True})


if __name__ == '__main__':
    app.run(debug=True)
