        function searchById() {
            const input = document.getElementById('search-input').value.toLowerCase();
            const rows = document.querySelectorAll('#user-table tbody tr');
            rows.forEach(row => {
                const idCell = row.querySelector('td[data-id]');
                if (idCell) {
                    const id = idCell.getAttribute('data-id').toLowerCase();
                    if (id.includes(input)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                }
            });
        }
