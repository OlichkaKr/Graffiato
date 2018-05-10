from flask import Flask, jsonify, abort, make_response, request

from CompanyManager import CompanyManager

app = Flask(__name__)


planes = []

company_manager = CompanyManager(planes)


@app.route('/planes', methods=['GET'])
def get_all_planes():
    return jsonify({'planes': planes})


@app.route('/planes/<int:plane_id>', methods=['GET'])
def get_plane(plane_id):
    plane = [plane for plane in planes if plane['id'] == plane_id]
    if len(plane) == 0:
        abort(404)
    return jsonify({'plane': plane[0]})


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)


@app.route('/planes', methods=['POST'])
def add_plane():
    if not request.json or not 'name' in request.json:
        abort(400)
    plane = {
        'id': planes[-1]['id'] + 1,
        'name': request.json['name'],
        'type': request.json.get('type', ""),
        'capacity': request.json.get('capacity', 0)
    }
    planes.append(plane)
    return 201


@app.route('/planes/<int:plane_id>', methods=['PUT'])
def update_plane(plane_id):
    plane = [plane for plane in planes if plane['id'] == plane_id]
    if len(plane) == 0:
        abort(404)
    if not request.json:
        abort(400)

    plane[0]['name'] = request.json.get('name', plane[0]['name'])
    plane[0]['type'] = request.json.get('type', plane[0]['type'])
    plane[0]['capacity'] = request.json.get('capacity', plane[0]['capacity'])
    return jsonify({'plane': plane[0]})


@app.route('/planes/<int:plane_id>', methods=['DELETE'])
def delete_plane(plane_id):
    plane = [plane for plane in planes if plane['id'] == plane_id]
    if len(plane) == 0:
        abort(404)
    planes.remove(plane[0])
    return jsonify({'result': True})


if __name__ == '__main__':
    app.run(debug=True)
